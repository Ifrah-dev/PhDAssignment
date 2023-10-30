# PhDAssignment
#Steps to clone  common-lang 
clone this git link https://github.com/apache/commons-lang using intelli
goto run Edit configuration 
Modify its options 
Select in code coverage 
specify alternative coverage runner 
select coverage runner as JoCOCO
Run with project with coverage 
Now .Exec file will be geneated in intellegi idea  by default 
Copy commons_lang$All_in_commons_lang3.Exec file from default output directory of intelijIdea Coverage in windows it is available at C:\Users\ifrah.komal\AppData\Local\JetBrains\IdeaIC2022.3\coverage 
Paste that opied file in common-lang clone directory in local pc.
Run phDAddignment Project after cloning in local pc
Edit Run Configurations
Add new Configurations
select Working directory as the project local directory where project is cloned
Run project
In console of output project will require 2 paths
First the directory path of comman-lang repo cloned local path
Second path required is of the output for .json file where jason data in required format will be saved
Please provide will path i.e "Download\test.json" file name and extention as well.
sample data output provided below
{"location":"D:\\TestingProject\\commons-lang","stat_of_repository":{"num_java_files":1112,"num_classes":1112,"num_methods":12363,"num_test_methods":3731},"test_coverage_against_methods":"[{\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#main\":[\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#main\"]}, {\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#lambda$main$1\":[\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#lambda$main$1\"],\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#main\":[\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#main\"]}, {\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#lambda$main$0\":[\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#lambda$main$0\"],\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#lambda$main$1\":[\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#lambda$main$1\"],\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#main\":[\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#main\"]}]"} 

![image](https://github.com/Ifrah-dev/PhDAssignment/assets/142144099/32bd3bce-2caf-4334-be3c-633e9a163cfa)



