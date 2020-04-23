# Projet_7_OC
Projet 7 OC de DA

Cette application a été testée avec la version 8.5 de tomcat.

Commencez par clonez le code de l'application en tapant en ligne de commande :

git clone https://github.com/RomainDemellier/Projet_7_OC.git

Vous aurez besoin de Maven pour faire fonctionner cette application. Vous pouvez télécharger Maven à cette adresse : https://maven.apache.org/download.cgi.

Après avoir téléchargé Maven tapez mvn --version en ligne de commande pour s'assurer que Maven a bien été téléchargé. Si vous voyez quelquechose comme suit c'est bon :

Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: D:\apache-maven-3.6.3\apache-maven\bin\..
Java version: 1.8.0_232, vendor: AdoptOpenJDK, runtime: C:\Program Files\AdoptOpenJDK\jdk-8.0.232.09-hotspot\jre
Default locale: en_US, platform encoding: Cp1250
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows" 

Pour builder l'application mettez vous sous la racine de l'application (cd Projet_7_OC/projet_7/). Ensuite tapez la commande suivante dans votre terminal :

mvn package

Il vous faut maintenant récupérer le fichier projet_7-0.0.1-SNAPSHOT.war. Sous la racine de l'application il y a un dossier nommé projet_7. Dans ce dossier se trouve un dossier nommé target dans lequel se trouve le fichier projet_7-0.0.1-SNAPSHOT.war. Il vous faut copier ce fichier et le collez dans un dossier nommé webapps. Ce dossier se trouve dans le dossier nommé apache-tomcat. Le nom de ce dossier peut varier selon les versions.

Pour continuer rendez vous sur la page https://github.com/RomainDemellier/Projet_7_OC_Batch et suivez les instructions du README.md.


