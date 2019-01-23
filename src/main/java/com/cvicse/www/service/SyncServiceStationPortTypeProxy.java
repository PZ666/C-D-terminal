package com.cvicse.www.service;

public class SyncServiceStationPortTypeProxy implements com.cvicse.www.service.SyncServiceStationPortType {
  private String _endpoint = null;
  private com.cvicse.www.service.SyncServiceStationPortType syncServiceStationPortType = null;
  
  public SyncServiceStationPortTypeProxy() {
    _initSyncServiceStationPortTypeProxy();
  }
  
  public SyncServiceStationPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initSyncServiceStationPortTypeProxy();
  }
  
  private void _initSyncServiceStationPortTypeProxy() {
    try {
      syncServiceStationPortType = (new com.cvicse.www.service.SyncServiceStationLocator()).getsyncServiceStationPort();
      if (syncServiceStationPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)syncServiceStationPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)syncServiceStationPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (syncServiceStationPortType != null)
      ((javax.xml.rpc.Stub)syncServiceStationPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.cvicse.www.service.SyncServiceStationPortType getSyncServiceStationPortType() {
    if (syncServiceStationPortType == null)
      _initSyncServiceStationPortTypeProxy();
    return syncServiceStationPortType;
  }
  
  public com.cvicse.www.service.SyncServiceStationOperationResponse syncServiceStationOperation(com.cvicse.www.service.SyncServiceStationOperationRequest parameters) throws java.rmi.RemoteException{
    if (syncServiceStationPortType == null)
      _initSyncServiceStationPortTypeProxy();
    return syncServiceStationPortType.syncServiceStationOperation(parameters);
  }
  
  
}