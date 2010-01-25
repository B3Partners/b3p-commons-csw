- Wanneer je dit project include in Netbeans, MOET je ook de "JAXB" Netbeans library (dedicated libs) toevoegen aan je libs in je Netbeans (web-)project.

- Wanneer je een JRE gebruikt die JAXB ook mee packaged (>= Java 1.6) zet dan "jaxb-api.jar" in de endorsed dir van tomcat of de jre (hier zijn we nog niet helemaal uit. Probeer eerst die van tomcat) aangezien je nu een systeem jar moet overriden.

- Wanneer je dit project build met een Java 6 SDK en met als code-base Java 5 (bijvoorbeeld om te deployen op een server die Java 5 draait), zorg er dan voor dat je build met JDK6(u>=10) (gebruik een JDK6 die minimaal update 10 (u10) is.)