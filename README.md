# A2-Venue-Manager
Venue hiring management program designed and built for RMIT Further Programming Assignment 2

## Dependencies
### Java
This program uses Java 21
### JavaFX
[Download JavaFX-sdk version 23.0.2](https://gluonhq.com/products/javafx/)
Unzip it to anywhere (I prefer c:\)
[Or Follow This Video](https://www.youtube.com/watch?v=bC4XB6JAaoU)

### SQLITE3 JDBC
[Download sqlite-jdbc-3.49.0.0.jar](https://github.com/xerial/sqlite-jdbc/releases)
Also unzip it to a folder in c:\

### Class Paths / Build Paths
These steps are for Eclipse on windows:
<ol>
  <li>Open the source code in Eclipse</li>
  <li>Navigate to the project's build path</li>
  <li>Click Classpath then Add Library</li>
  <li>Add JavaFX SDK</li>
  <li>Click ModulePath then Add Library</li>
  <li>Then click User Library</li>
  <li>If you do not have JavaFX in the following list click User Libraries... in the top right hand corner</li>
  <li>Click new and name it JavaFX</li>
  <li>Click JavaFX and then Add External JARS</li>
  <li>Select all of the extracted files from c:\</li>
  <li>Click new and name it JDBCsqlite</li>
  <li>Click JDBCsqlite</li>
  <li>Click Add External JARS and add the extracted JDBC driver from c:\</li>
  <li>Apply these and add them to your Classpath</li>
  <li>It should now look like this</li>
  <ul>
    <li>Modulepath</li>
    <li>   JavaFX</li>
    <li>   JRE System Library [JavaSE-21]</li>
    <li>Classpath</li>
    <li>   JavaFX SDK</li>
    <li>   JDBCsqlite</li>
  </ul>
  <li>After that apply changes and try to run Main</li>
  <li>It should crash, click on the down arrow next to run and click 'Run Configurations'</li>
  <li>From there navigate to Java Application > Main > Arguments and enter</li>
  <p>   --module-path "PATHTOJAVAFXFILE\lib" --add-modules javafx.controls,javafx.fxml</p>
  <p>to the Program arguments and VM arguments</p>
  <li>It should now run in eclipse, that is all I have time to explain, this might be updated later.</li>
</ol>
