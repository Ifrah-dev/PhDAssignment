package org.example;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.bcel.classfile.ClassParser;
import org.jacoco.core.analysis.*;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.data.SessionInfoStore;
import org.jacoco.core.instr.Instrumenter;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.core.data.ExecutionDataReader;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.data.SessionInfoStore;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.analysis.Analyzer;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
// new file
public class Main {
    public static void main(String[] args) throws IOException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
        System.out.print("Enter Directory Path: ");
        String str= sc.nextLine(); //reads string
        System.out.print("Enter Output Path: ");
        String outputfile= sc.nextLine(); //reads string


        // Specify the directory path of your Java project
        String projectDirectoryPath = str;

        // Create a File object representing the project directory
        File projectDirectory = new File(projectDirectoryPath);

        // Create a FileFilter to filter .class files
        FileFilter classFileFilter = file ->  file.getName().endsWith(".class");

        // Use listFiles() to get all .class files and count them
        //int classCount = countFiles(projectDirectory, classFileFilter);

        List<String> javafiles =  listjavaFiles(String.valueOf(projectDirectoryPath));
        List<File> classFiles = new ArrayList<>();
        int[] counts =  countMethods(String.valueOf(projectDirectoryPath),classFiles);
        try {
            String list =  coverageInfo(String.valueOf(projectDirectoryPath),classFiles);

        // Print the number of classes

        Root objectRoot = new Root();
        objectRoot.location = projectDirectoryPath;
        objectRoot.stat_of_repository = new StatOfRepository();
        objectRoot.stat_of_repository.num_classes = counts[2];
        objectRoot.stat_of_repository.num_java_files = javafiles.size();
        objectRoot.stat_of_repository.num_methods = counts[0];
        objectRoot.stat_of_repository.num_test_methods = counts[1];

        //objectRoot.test_coverage_against_methods = new TestCoverageAgainstMethods();
        objectRoot.test_coverage_against_methods = list;

            ObjectMapper objectMapper = new ObjectMapper();

            // Serialize object to JSON
            String jsonString = objectMapper.writeValueAsString(objectRoot);

            try {
                Path path = Paths.get(outputfile);
                byte[] jsonData = jsonString.getBytes();

                Files.write(path, jsonData);
                System.out.println("JSON data has been written to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }
            System.out.println( "Final Result " +jsonString);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    private static List<String> listjavaFiles(String directoryPath) throws IOException {
        try (Stream<Path> walk = Files.walk(Paths.get(directoryPath))) {
            return walk
                    .filter(path -> path.toString().endsWith(".class"))
                    .map(path -> {
                        String className = path.toString()
                                .replace(File.separator, ".") // Convert path separator to package separator
                                .replace(".java", ""); // Remove .class extension
                        return className.substring(className.lastIndexOf(".") + 1); // Extract class name
                    })
                    .collect(Collectors.toList());
        }
    }
    private static int[] countMethods(String directoryPath, List<File> classFiles) throws IOException {

        // Create a File object representing the project directory
        File projectDirectory = new File(directoryPath);

        List<Integer> methodCounts = new ArrayList<>();
        // Verify that the directory exists
        if (!projectDirectory.exists() || !projectDirectory.isDirectory()) {
            System.out.println("Invalid project directory path.");
            //return 0;
        }
        // List to store class
        //List<File> classFiles = new ArrayList<>();
        findClasses(projectDirectory, classFiles);
        // Create a FileFilter to filter .class files
        FileFilter classFileFilter = file -> file.isFile() && file.getName().endsWith(".class");

        int totalMethodCount = 0;
        int totalTestMethodsCount = 0;
        int totalClassesCount =0;
        totalClassesCount = classFiles.size();
        // Iterate through each class file and extract package information
        for (File classFile : classFiles) {
            ClassParser classParser = new ClassParser(classFile.getAbsolutePath());
            JavaClass javaClass = classParser.parse();
            // Get package name
            String packageName = javaClass.getPackageName();

            // Get class name
            String className = javaClass.getClassName();
            int methodsCount = Arrays.asList(javaClass.getMethods()).size();
            Method[] methods = javaClass.getMethods();
            // Iterate through the methods and find those annotated with @Test
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("test") && methodName.length() > 4 &&
                        Character.isUpperCase(methodName.charAt(4))) {
                    totalTestMethodsCount++;
                    //System.out.println(methodName);
                }
            }
            totalMethodCount = totalMethodCount+methodsCount;

        }
        int[] result = new int[3];
        result[0] =totalMethodCount;
        result[1] =totalTestMethodsCount;
        result[2] = totalClassesCount;
        return  result;
    }

    private static void findClasses(File directory, List<File> classNames) {
        // List .class files in the current directory
        File[] classFiles = directory.listFiles(file -> file.isFile() && file.getName().endsWith(".class"));

        // Add class names to the list
        if (classFiles != null) {
            for (File classFile : classFiles) {
                // Convert file path to class name (example: /path/to/YourClass.class -> YourClass)
                //String className = classFile.getName().replace(".class", "");
                classNames.add(classFile);
            }
        }

        // Recursively search in subdirectories
        File[] subdirectories = directory.listFiles(File::isDirectory);
        if (subdirectories != null) {
            for (File subdir : subdirectories) {
                findClasses(subdir, classNames);
            }
        }
    }

     private static String coverageInfo(String directoryPath, List<File> classFiles) throws Exception {
        // Load JaCoCo runtime
       /* IRuntime runtime = new LoggerRuntime();
        RuntimeData data = new RuntimeData();
        runtime.startup(data);*/

        // Read coverage data from .exec file
        File execFile = new File(directoryPath+"\\commons_lang$All_in_commons_lang3.exec");
         ExecutionDataStore executionDataStore = new ExecutionDataStore();
         SessionInfoStore sessionInfoStore = new SessionInfoStore();

         try (FileInputStream execInputStream = new FileInputStream(execFile)) {
             ExecutionDataReader reader = new ExecutionDataReader(execInputStream);
             reader.setExecutionDataVisitor(executionDataStore);
             reader.setSessionInfoVisitor(sessionInfoStore);
             reader.read();
         }

         // Analyze the loaded execution data
         CoverageBuilder coverageBuilder = new CoverageBuilder();
         Analyzer analyzer = new Analyzer(executionDataStore, coverageBuilder);


         for (File classFile : classFiles) {
             // Load your class file (replace with the actual class name)
            // byte[] classBytes = loadClassBytes(classFile.getName());; // Load class bytes from the class file

             byte[] classBytes =  Files.readAllBytes(classFile.getAbsoluteFile().toPath()); ;
             analyzer.analyzeClass(classBytes, classFile.getName());
         }

         ObjectMapper objectMapper = new ObjectMapper();
         Map<String, List<String>> testCoverageMap = new HashMap<>();
         List<String> jsonStringList = new ArrayList<>();;

         for (IClassCoverage classCoverage : coverageBuilder.getClasses()) {
             String className = classCoverage.getName();

                 for (IMethodCoverage methodCoverage : classCoverage.getMethods()) {
                     String methodName = methodCoverage.getName();
                     boolean isMethodCovered = methodCoverage.getLineCounter().getCoveredCount() > 0;
                     //boolean isTestMethod = methodName.startsWith("test"); // Customize this check based on your naming convention

                     //if (isTestMethod) {
                         if (isMethodCovered) {
                             // Assuming methodName is in the format "org.example.ClassName#methodName"
                             String fullMethodName = className + "#" + methodName;
                             testCoverageMap.computeIfAbsent(fullMethodName, k -> new ArrayList<>()).add(fullMethodName);
                             jsonStringList.add(objectMapper.writeValueAsString(testCoverageMap));

                         }
                    // }
                 }

         }

         return jsonStringList.toString();
    }

}
