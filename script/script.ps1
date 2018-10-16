Remove-Item -Recurse -Force "azure-maven-archetypes" -ErrorAction Ignore
git clone https://github.com/Microsoft/azure-maven-archetypes.git -b develop
mvn -f  ".\azure-maven-archetypes\azure-functions-archetype\pom.xml" clean install
$archetypePom =  Get-Content ".\azure-maven-archetypes\azure-functions-archetype\pom.xml" -Raw
$archetypePom -match "<version>(.*)</version>"
$atchetypeVersion = $matches[1]

# Generate function project through archetype
Remove-Item -Recurse -Force ".\e2etestproject" -ErrorAction Ignore
mkdir e2etestproject
cd e2etestproject
mvn archetype:generate -DarchetypeCatalog="local" -DarchetypeGroupId="com.microsoft.azure" -DarchetypeArtifactId="azure-functions-archetype" -DarchetypeVersion="$atchetypeVersion" -DgroupId="com.microsoft" -DartifactId="e2etestproject" -Dversion="1.0-SNAPSHOT" -Dpackage="com.microsoft" -DappRegion="westus" -DresourceGroup="e2etest-java-functions-group" -DappName="e2etest-java-functions"  -B
mvn -f ".\e2etestproject\pom.xml" clean package