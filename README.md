# JPA Notes

This project is a simulation of a Java EE application using the JPA architecture, but abstracting from the top level user interface layer and focusing in the Domain Model and DAOs.

Starting from the basis of "An User can take a Note and label it with some Tags", this project simulates the developing steps of a software. In the section architecture requirements, we had "Use JPA to mapping java objects to MySQL database" as restriction, so from then on, we continued specifying how our Java objects will be mapped to a traditional MySQL database by using JPA. We have focused on separating the responsabilities into three main layers: Model, DAOs and Business Logic. The DAOs are the only ones accessing directly to the database through the EntityManager.

[Read more (Documentation.pdf)](https://github.com/Maximetinu/JPA-Notes/blob/master/Documentation.pdf "Documentation.pdf")

### Notes
* Developed with Eclipse EE with Eclipselink
* Project for Software Architectures And Methodologies (SWAM), Università degli Studi di Firenze - UniFI

### Usage
1. Open JPA Notes Project folder with EclipseEE as a project. Eclipselink dependencies will be lost.
2. From the Navigator or Project explorer, right-click the Java project and then select Configure > Convert to JPA Project. The Project Facets page of the Modify Faceted Project wizard appears.
3. Launch MySQL database in `jdbc:mysql://localhost:3306/notesdb"/` with `username = "root"` and `password = ""`

All dependencies should work also with another JPA implementations as Hibernate, as we were careful to use only standard specification annotations and properties. The only excepction is the property `eclipselink.logging.level` in Persistence.xml. **Should work but not tested.**

### Authors
- [Javier Izquierdo Vera (Lifka)](https://github.com/Lifka/) - [javierizquierdovera@gmail.com](mailto:javierizquierdovera@gmail.com)
- [Miguel Medina Ballesteros (Maximetinu)](https://github.com/Maximetinu/) - [maximetinu@gmail.com](mailto:maximetinu@gmail.com)
- Docente: [Prof. Enrico Vicario](http://www.dsi.unifi.it/~vicario/) - [enrico.vicario@unifi.it](mailto:enrico.vicario@unifi.it)


### License
Non-Profit Open Software License version 3.0 (NPOSL-3.0) https://opensource.org/licenses/NPOSL-3.0


### Architecture Diagram
![Class diagram](https://github.com/Maximetinu/JPA-Notes/blob/master/Diagrams/JPAArchitecture.png?raw=true)


### Class Diagram of Model

![Class diagram](https://raw.githubusercontent.com/Maximetinu/JPA-Notes/master/Diagrams/Class%20Diagram1.jpg)


### Screenshots

![Screenshot 1](https://raw.githubusercontent.com/Maximetinu/JPA-Notes/master/screenshots/screenshot1.png)
![Screenshot 2](https://raw.githubusercontent.com/Maximetinu/JPA-Notes/master/screenshots/screenshot2.png)
![Screenshot 3](https://raw.githubusercontent.com/Maximetinu/JPA-Notes/master/screenshots/screenshot3.png)
![Screenshot 4](https://raw.githubusercontent.com/Maximetinu/JPA-Notes/master/screenshots/screenshot4.png)
![Screenshot 5](https://raw.githubusercontent.com/Maximetinu/JPA-Notes/master/screenshots/screenshot5.png)


### Database

![Domain conceptual schema](https://raw.githubusercontent.com/Maximetinu/JPA-Notes/master/Diagrams/Domainconceptualschema.jpg)
![Screenshot 6](https://raw.githubusercontent.com/Maximetinu/JPA-Notes/master/screenshots/screenshot6.png)
