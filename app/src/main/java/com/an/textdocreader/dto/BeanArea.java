package com.an.textdocreader.dto;

import java.util.ArrayList;

public class BeanArea {

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

    private String name;
    private boolean enabled;
    private ArrayList<BeanContact> contacts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ArrayList<BeanContact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<BeanContact> contacts) {
        this.contacts = contacts;
    }
}
