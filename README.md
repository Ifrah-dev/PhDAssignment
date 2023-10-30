# PhDAssignment
#Steps to clone  common-lang 
1: clone this git link https://github.com/apache/commons-lang using intelli
2:goto run Edit configuration 
3:Modify its options 
4:Selet in code coverage 
5:specify alternative coverage runner 
6: select coverage runner as JoCOCO
7:Run with project with coverage 
8:Now .Exec file will be geneated in intellegi idea  by default 
9:Coy commons_lang$All_in_commons_lang3.Exec file from default output directory of intelijIdea Coverage in windows it is available at C:\Users\ifrah.komal\AppData\Local\JetBrains\IdeaIC2022.3\coverage 
10:Paste that opied file in common-lang clone directory in local pc.
11: Run phDAddignment Project after cloning in local pc
12. Edit Run Configurations
13. Add new Configurations
14. select Working directory as the project local directory where project is cloned
15. Run project
16. in console of output project will require 2 paths
17. first the directory path of comman-lang repo cloned local path
18. second path required is of the output for .json file where jason data in required format will be saved
19. Please provide will path i.e "Download\test.json" file name and extention as well.
sample dat output provided below
{"location":"D:\\TestingProject\\commons-lang","stat_of_repository":{"num_java_files":1112,"num_classes":1112,"num_methods":12363,"num_test_methods":3731},"test_coverage_against_methods":"[{\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#main\":[\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#main\"]}, {\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#lambda$main$1\":[\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#lambda$main$1\"],\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#main\":[\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#main\"]}, {\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#lambda$main$0\":[\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#lambda$main$0\"],\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#lambda$main$1\":[\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#lambda$main$1\"],\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#main\":[\"org/apache/commons/lang3/mutable/PrintAtomicVsMutable#main\"]}]"} 



