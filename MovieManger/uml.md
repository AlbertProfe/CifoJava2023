# UML Movie 

``` mermaid
classDiagram
  class Movie {
    -title: String
    -reviews: List<Review>
    -director: Director
  }
  class Critic {
    -name: String
  }
  
  class Director {
    -name: String
  }
  class Review {
    -rating: int
    -comment: String
  }
  Movie *-- Review
  Movie *-- Director
  Review o-- Critic

```



