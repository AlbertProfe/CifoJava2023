# UML Movie 

``` mermaid
classDiagram
  class Movie {
    -title: String
    -reviews: List<Review>
  }
  class Critic {
    -name: String
  }
  class Review {
    -critic: Critic
    -rating: int
    -comment: String
  }
  Movie *-- Review
  Review o-- Critic

```



