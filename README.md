## grapql4j


`GraphQL, a query language and execution engine originally created at Facebook in 2012 for describing the 
capabilities and requirements of data models for client‐server applications.
`

_We noticed that their where not many implementations in java which support this query language so we decided to do something about it._

## Goal

  - The goal of this project is to generate a datamodel from any graphql schema together with generating a query language model.



## Usage

    usage: java eu.ludimus.graphql.generator.ParserMain <schema path> <packagename> <output path>
    
    e.g: java eu.ludimus.graphql.generator.ParseMain ${basedir}/src/test/resources/tweet.graphql eu.ludimus.tweet ${basedir}/target/generated-sources
