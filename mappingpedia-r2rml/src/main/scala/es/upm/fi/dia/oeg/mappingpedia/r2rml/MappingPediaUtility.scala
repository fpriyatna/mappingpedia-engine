package es.upm.fi.dia.oeg.mappingpedia.r2rml

import scala.None
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.Resource
import org.apache.jena.rdf.model.Literal
import org.apache.jena.graph.Triple
import virtuoso.jena.driver.VirtGraph
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import java.io.ByteArrayInputStream
import org.apache.jena.util.FileManager


object MappingPediaUtility {
	val logger : Logger = LogManager.getLogger("MappingPediaUtility");

  def getFirstPropertyObjectValueLiteral(resource:Resource, property:Property): Literal = {
		val it = resource.listProperties(property);
		var result: Literal = null;
		if(it != null && it.hasNext()) {
			val statement = it.next();
			val objectNode = statement.getObject();
			result = objectNode.asLiteral()
		}
		return result;
  }

  def getVirtuosoGraph(virtuosoJDBC : String, virtuosoUser : String, virtuosoPwd : String
		, virtuosoGraphName : String) : VirtGraph = {
				logger.info("Connecting to Virtuoso Graph.");
				val virtGraph : VirtGraph = new VirtGraph (
						virtuosoGraphName, virtuosoJDBC, virtuosoUser, virtuosoPwd);

				return virtGraph;
  }

  def store(triples : Iterable[Triple], virtuosoGraph : VirtGraph) = {
		val initialGraphSize = virtuosoGraph.getCount();
		logger.debug("initialGraphSize = " + initialGraphSize);

		val triplesIterator = triples.iterator;
		while(triplesIterator.hasNext) {
			val triple = triplesIterator.next();
			virtuosoGraph.add(triple);
		}

		val finalGraphSize = virtuosoGraph.getCount();
		logger.debug("finalGraphSize = " + finalGraphSize);

		val addedTriplesSize = finalGraphSize - initialGraphSize; 
		logger.info("No of added triples = " + addedTriplesSize);	  
  }
  
  def readModelFromString(modelText:String, lang:String) : Model = {
    val model = ModelFactory.createDefaultModel();
    val is = new ByteArrayInputStream(modelText.getBytes());
    model.read(is, null, lang);
    model;
  }
  
  def readModelFromFile(filePath:String, lang:String, rdfSyntax:String) : Model = {
		val inputStream = FileManager.get().open(filePath);
		val model = ModelFactory.createDefaultModel();
		model.read(inputStream, null, rdfSyntax);	 
		model;
  }
}