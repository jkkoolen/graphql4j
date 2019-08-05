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
    
    
