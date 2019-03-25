package es.upm.fi.dia.oeg.mappingpedia.test

import es.upm.fi.dia.oeg.mappingpedia.connector.RMLMapperConnector
import org.slf4j.{Logger, LoggerFactory}

object TestRMLConnector {
  val logger: Logger = LoggerFactory.getLogger(this.getClass);

  def main(args:Array[String]) = {
    val distributionDownloadURL = "https://www.zaragoza.es/sede/servicio/equipamiento/category/1582.json"
    val mappingDocumentDownloadURL = "https://raw.githubusercontent.com/oeg-upm/mappingpedia-contents/master/zaragoza_spain/778c2bb1-4f47-4f1a-bcbe-60a9e8d1486b/25TBH8BEDL.rml.ttl"
    val outputFilePath = "./result.txt"

    val rmlConnector = new RMLMapperConnector();
    rmlConnector.executeWithMain(distributionDownloadURL, mappingDocumentDownloadURL, outputFilePath)
    logger.info("Bye");
  }
}
