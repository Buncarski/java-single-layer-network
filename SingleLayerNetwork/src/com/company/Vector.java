package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Vector {
    public List<Double> positions = new ArrayList<>();
    private String vClass;

    public Vector(String s) {
        String[] tmp = s.split(",");
        for(int i=0; i < tmp.length; i++){
            if(i < tmp.length-1){
                this.positions.add(Double.parseDouble(tmp[i]));
            } else {
                if(tmp[i].equals("null")){
                    this.vClass = null;
                } else {
                    this.vClass = tmp[i];
                }
            }
        }
        this.positions = normalizeVector();
    }

    public Vector(Map<Character, Double> map, String vClass){
        for (Map.Entry<Character, Double> entry : map.entrySet()) {
            this.positions.add(map.get(entry.getKey()));
        }
        this.vClass = vClass;
        this.positions = normalizeVector();
    }

    public List<Double> getPositions() {
        return positions;
    }

    public String getvClass() {
        return vClass;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "positions=" + positions +
                ", vClass='" + vClass + '\'' +
                '}';
    }

    public void updateVector(Vector x, Double learningRate, int delta, int y){
        for(int i = 0; i < this.positions.size(); i++){
            Double value = this.positions.get(i) + learningRate*(delta-y)*x.getPositions().get(i);
            this.positions.set(i, value);
        }
    }

    public List<Double> normalizeVector(){
        List<Double> positions = new ArrayList<>();
        Double vLength = this.getLength();

        for(int i = 0; i < this.positions.size(); i++){
            positions.add(this.positions.get(i)/vLength);
        }
            return positions;
    }

    public Double getLength(){
        Double vLength = (double) 0;
        for(int i = 0; i < this.positions.size(); i++){
            vLength += Math.pow(this.positions.get(i),2);
        }
        vLength = Math.sqrt(vLength);

        return vLength;
    }
}

