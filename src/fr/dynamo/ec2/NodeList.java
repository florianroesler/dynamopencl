package fr.dynamo.ec2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class NodeList {

  private final String nodeFilePath = "./dcl.nodes";
  private Set<String> nodes = new HashSet<String>();

  public NodeList(){
    serialize();
  }

  public void addNode(String ip){
    synchronized(nodes){
      nodes.add(ip);
      serialize();
    }
  }

  public void removeNode(String ip){
    synchronized(nodes){
      nodes.remove(ip);
      serialize();
    }
  }

  public String[] getNodes(){
    synchronized(nodes){
      return (String[]) nodes.toArray();
    }
  }

  private void serialize(){
    StringBuffer buffer = new StringBuffer();

    for(String node:nodes){
      buffer.append(node+"\n");
    }

    try {
      Files.write(Paths.get(nodeFilePath), buffer.toString().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
