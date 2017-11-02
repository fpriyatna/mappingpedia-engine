package es.upm.fi.dia.oeg.mappingpedia.model.result

class ExecuteMappingResult (
                             val errorCode:Integer, val status:String
                             , val datasetURL:String, val mappingURL:String
                             , val queryURL:String
                             , val mappingExecutionResultURL:String, val mappingExecutionResultDownloadURL:String
                             , val ckanResponseStatus:Integer
                           , val mappingExecutionResultId:String
                           , val manifestURL:String
                           ){

  def getDatasetURL() = datasetURL;
  def getMappingURL() = mappingURL;
  def getQueryURL() = queryURL;
  def getMappingExecutionResultURL() = mappingExecutionResultURL;
  def getStatus() = status;
  def getErrorCode() = errorCode;
  def getMappingExecutionResultDownloadURL = mappingExecutionResultDownloadURL;
  def getCKANResponseStatus = ckanResponseStatus;
  def getMappingExecutionResultId = this.mappingExecutionResultId
  def getManifestURL = this.manifestURL

}
