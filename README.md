# JPA Notes

This project is a simulation of a Java EE application using the JPA architecture, but abstracting from the top level user interface layer and focusing in the Domain Model and DAOs.

Starting from the basis of "An User can take a Note and label it with some Tags", this project simulates the developing steps of a software. In the section architecture requirements, we had "Use JPA to mapping java objects to MySQL database" as restriction, so from then on, we continued specifying how our Java objects will be mapped to a traditional MySQL database by using JPA. We have focused on separating the responsabilities into three main layers: Model, DAOs and Business Logic. The DAOs are the only ones accessing directly to the database through the EntityManager.

[Read more (Documentation.pdf)](https://github.com/Maximetinu/JPA-Notes/blob/master/Documentation.pdf "Documentation.pdf")

### Notes
* Developed with Eclipse EE with Eclipselink
* Project for Software Architectures And Methodologies (SWAM), Università degli Studi di Firenze - UniFI


### Authors
- [Javier Izquierdo Vera (Lifka)](https://github.com/Lifka/) - [javierizquierdovera@gmail.com](mailto:javierizquierdovera@gmail.com)
- [Miguel Medina Ballesteros (Maximetinu)](https://github.com/Maximetinu/) - [maximetinu@gmail.com](mailto:maximetinu@gmail.com)


### License
Non-Profit Open Software License version 3.0 (NPOSL-3.0) https://opensource.org/licenses/NPOSL-3.0


### Architecture Diagram
![Class diagram](https://github.com/Maximetinu/JPA-Notes/blob/master/Diagrams/JPAArchitecture.png?raw=true)


### Class Diagram

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
