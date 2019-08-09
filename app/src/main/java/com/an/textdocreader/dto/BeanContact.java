package com.an.textdocreader.dto;

public class BeanContact {

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

    private String attribute1, attribute2;
    private boolean enabled;
    private String[] images;

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
