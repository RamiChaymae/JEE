
This application is based on a set of modern technologies, including Java, the Spring Framework, Hibernate, MySQL, and Thymeleaf for the creation of dynamic user interfaces. It allows users to view, add, modify and delete items, while displaying detailed lists with relevant information, with user role management. Each role has a specific set of permissions to access pages and perform corresponding operations. Spring Security is used to manage user authentication and authorisation.

La page de login de notre application offre aux utilisateurs la possibilité de s’identifieren fournissant leurs informations d’identification, telles que leur adresse e-mail et leur mot de passe.

![image](https://github.com/RamiChaymae/JEE/assets/136628810/79c48159-716a-42d6-b88e-5586a5160956)

After a correct authentication this page is displayed:
![image](https://github.com/RamiChaymae/JEE/assets/136628810/de97319b-7636-493a-98fc-eb23dc9d9726)


**Administrator** : Access an administrative dashboard containing an overview of current projects, members and publications. It also contains a graph showing the number of members by role. The administrator has the right to **view, add, modify and delete** laboratory members, their roles, laboratory projects, publications and resources.

**Director** : they have the right to **see** laboratory members, laboratory projects, publications and resources.

**Teachers** : they have the right to **see** laboratory projects, publications and resources, and **add** publications.

**Doctaurant** : they have the right to **see** the laboratory's projects, publications and resources.
