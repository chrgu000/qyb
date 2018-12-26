package com.thinkgem.jeesite.common.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;

public class XmlMapHandle {

  /**
   * 将Document对象转为Map（String→Document→Map）
   *
   * @param doc
   * @return
   */
  public static Map<String, Object> Dom2Map(Document doc) {
    Map<String, Object> map = new HashMap<>();
    if (doc == null)
      return map;
    Element root = doc.getRootElement();
    for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
      Element e = (Element) iterator.next();
      //System.out.println(e.getName());
      List list = e.elements();
      if (list.size() > 0) {
        map.put(e.getName(), Dom2Map(e));
      } else
        map.put(e.getName(), e.getText());
    }
    return map;
  }

  /**
   * 将Element对象转为Map（String→Document→Element→Map）
   *
   * @param e
   * @return
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public static Map Dom2Map(Element e) {

    Map map = new HashMap();
    List list = e.elements();
    if (list.size() > 0) {
      for (int i = 0; i < list.size(); i++) {
        Element iter = (Element) list.get(i);
        List mapList = new ArrayList();
        if (iter.elements().size() > 0) {
          Map m = Dom2Map(iter);
          if (map.get(iter.getName()) != null) {
            Object obj = map.get(iter.getName());
            if (!obj.getClass().getName().equals("java.util.ArrayList")) {
              mapList = new ArrayList();
              mapList.add(obj);
              mapList.add(m);
            }
            if (obj.getClass().getName().equals("java.util.ArrayList")) {
              mapList = (List) obj;
              mapList.add(m);
            }
            map.put(iter.getName(), mapList);
          } else
            map.put(iter.getName(), m);
        } else {
          if (map.get(iter.getName()) != null) {
            Object obj = map.get(iter.getName());
            if (!obj.getClass().getName().equals("java.util.ArrayList")) {
              mapList = new ArrayList();
              mapList.add(obj);
              mapList.add(iter.getText());
            }
            if (obj.getClass().getName().equals("java.util.ArrayList")) {
              mapList = (List) obj;
              mapList.add(iter.getText());
            }
            map.put(iter.getName(), mapList);
          } else
            map.put(iter.getName(), iter.getText());//公共map resultCode=0
        }
      }
    } else
      map.put(e.getName(), e.getText());
    return map;
  }


  public static Map domToMap(String xml) {
    try {
      Document doc = DocumentHelper.parseText(xml);
      Element rootElt = doc.getRootElement();
      return Dom2Map(rootElt);
    } catch (DocumentException e) {
      e.printStackTrace();
    }
    return new HashMap();
  }

}
