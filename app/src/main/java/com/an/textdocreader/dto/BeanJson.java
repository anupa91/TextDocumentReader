package com.an.textdocreader.dto;

import java.util.ArrayList;

public class BeanJson {

    /*
    *
    * {
  "areas": [
    {
      "name": "Sinhala Text",
      "enabled": true,
      "contacts": [
        {
          "attribute1": "Attribute1 value",
          "attribute2": "Attribute2 value",
          "enabled": true,
          "images": [
            "abc",
            "pqr"
          ]
        },
        {
          "attribute1": "Attribute1 value 1",
          "attribute2": "Attribute2 value 1",
          "enabled": false,
          "images": [
            "qwe",
            "rty"
          ]
        }
      ]
    },
    {
      "name": "Sinhala Text 2",
      "enabled": false,
      "contacts": [
        {
          "attribute1": "Attribute1 value 2",
          "attribute2": "Attribute2 value 2",
          "enabled": true,
          "images": [
            "lmn",
            "xyz"
          ]
        }
      ]
    }
  ]
}
    * */

    private ArrayList<BeanArea> areas;

    public ArrayList<BeanArea> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<BeanArea> areas) {
        this.areas = areas;
    }
}
