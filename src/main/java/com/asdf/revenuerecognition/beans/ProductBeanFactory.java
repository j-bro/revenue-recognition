package com.asdf.revenuerecognition.beans;

import com.asdf.revenuerecognition.strategies.CompleteRecognitionStategy;
import com.asdf.revenuerecognition.strategies.ThreeWayRecognitionStategy;

/**
 * Created by jeremybrown on 2016-11-18.
 */
public class ProductBeanFactory {

    public static ProductBean newWordProcessor(String name){
        return new ProductBean(name, new CompleteRecognitionStategy());
    }

    public static ProductBean newSpreadSheet(String name){
        return new ProductBean(name, new ThreeWayRecognitionStategy(60, 90));
    }

    public static ProductBean newDatabase(String name){
        return new ProductBean(name, new ThreeWayRecognitionStategy(30, 60));
    }
}
