Feature: Documentation

  Scenario: Save posts from remote server
    Given remote server url "https://jsonplaceholder.typicode.com/posts"
    When client fetches all posts from remote server
    Then all posts are stored in "src/test/resources" with each post in a separate file with name post_id.json