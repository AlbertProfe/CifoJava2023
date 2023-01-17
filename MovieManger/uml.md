# UML Movie 

``` mermaid
classDiagram
  class Movie {
    -title: String
    -reviews: List~Review~
    -director: Director
  }
  
  class Critic {
    -publications: int
  }
  
  class Director {
    -oscars: int
  }
  class Review {
    -rating: int
    -comment: String
    -critic: Critic
  }
  class Person {
    -name: String
  }
  
  class MovieManager {
    -static boolean deleteMovie(id)$
    -static boolean createMovie(id)$
    -static boolean updateMovie(id)$
    -getAllMovies()$ List~Movie~
    -findMovie(id)$ List~Movie
  }
  
  MovieManager <.. Movie
  Movie *-- Review
  Movie *-- Director
  Review o-- Critic
  Person <-- Director
  Person <-- Critic

```



