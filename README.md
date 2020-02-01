Preftwig2Stack

An exact query on an XML document is a query in which the user specifies precisely what he
wants to check or find in the document. XML documents are generally semi-structured.
When exact queries are applied to them, there is a great risk of obtaining an empty result
(too specific queries) or too important (too vague queries). In contrast to exact queries,
preference queries aim to return all the best results so that empty or overly important
results are avoided.

Here is a Java Standalone application for querying XML documents with requests with preferences based on an XQuery extension proposed for this purpose. Using Generalized Tree Patern (GTP) for query representation
