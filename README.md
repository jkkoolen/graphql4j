## grapql4j


`GraphQL, a query language and execution engine originally created at Facebook in 2012 for describing the 
capabilities and requirements of data models for client‐server applications.
`

_We noticed that their where not many implementations in java which support this query language so we decided to do something about it._

## Goal

  - The goal of this project is to generate a datamodel from any graphql schema together with generating a query language model.



## Usage for parsing the schema

    usage: java eu.ludimus.graphql.generator.ParserMain <schema path> <packagename> <output path>
    
    e.g: java eu.ludimus.graphql.generator.ParseMain ${basedir}/src/test/resources/tweet.graphql eu.ludimus.tweet ${basedir}/target/generated-sources

## How the result can be used
    After running the parserMain, you end up with a <packagename>.model and a <packagename>.query package.
    The model can be used for marshalling the result from the graphql server.
    The query can be used to create the actual data for the post query.
    
e.g: with this schema
        
        type Tweet {
            ID: ID!
            # The tweet text. No more than 140 characters!
            body: String
            # When the tweet was published
            date: Date
            # Who published the tweet
            Author: User
            # Views, retweets, likes, etc
            Stats: Stat
        }

        type User {
            ID: ID!
            username: String
            first_name: String
            last_name: String
            full_name: String
            name: String @deprecated
            avatar_URL: URL
        }

        type Stat {
            views: Int
            likes: Int
            retweets: Int
            responses: Int
        }

        type Notification {
            ID: ID
            date: Date
            type: String
        }

        type Meta {
            count: Int
        }

        scalar URL
        scalar Date

        type Query {
            Tweet(ID: ID!): Tweet
            Tweets(limit: Int, skip: Int, sort_field: String, sort_order: String): [Tweet]
            TweetsMeta: Meta
            User(ID: ID!): User
            Notifications(limit: Int): [Notification]
            NotificationsMeta: Meta
        }

        type Mutation {
            createTweet (
                body: String
            ): Tweet
            deleteTweet(ID: ID!): Tweet
            markTweetRead(ID: ID!): Boolean
        }

it generates a Query class in the query package which can be used like:

    Query.Tweets(3, 2, "methodName", "asc")
                .withResponse(Tweet.graphql().includeAuthor(
                        User.graphql().includeAvatar_URL().includeFirst_name().includeFull_name())
                        .includeBody()
                        .includeDate()
                        .includeID()
                        .includeStats(Stat.graphql().includeLikes().includeResponses().includeRetweets().includeViews()))
                .toQuery();
                
which gives the following query:                

    Tweets(limit: "3", ,skip: "2", ,sort_field: "methodName", ,sort_order: "asc", ){ID body date Author {first_name full_name avatar_URL }Stats {views likes retweets responses }}
