package com.ben.aoc;

import com.ben.aoc.collection.SetUtil;
import org.javatuples.Pair;
import org.jgrapht.Graph;
import org.jgrapht.alg.clique.BronKerboschCliqueFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import java.util.*;

public class Day23 {
    private Graph<String, DefaultEdge> network;

    public Day23(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        network = new DefaultUndirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        for(String line : lines){
            String[] link = line.split("-");
            network.addVertex(link[0]);
            network.addVertex(link[1]);
            network.addEdge(link[0], link[1]);
        }
    }


    public Long puzzle1(){
        Set<List<String>> subNetworks = new HashSet<>();

        for(String vertex : network.vertexSet()){
            if(vertex.startsWith("t")) {
                Set<String> neighbours = new HashSet<>();
                for (DefaultEdge edge : network.edgesOf(vertex)) {
                    if (network.getEdgeTarget(edge).equals(vertex)) {
                        neighbours.add(network.getEdgeSource(edge));
                    } else {
                        neighbours.add(network.getEdgeTarget(edge));
                    }
                }

                for (Pair<String, String> vertexPair : SetUtil.getPairs(neighbours)) {
                    if (network.containsEdge(vertexPair.getValue0(), vertexPair.getValue1()) || network.containsEdge(vertexPair.getValue1(), vertexPair.getValue0())) {
                        List<String> subNetwork = new ArrayList<>();
                        subNetwork.add(vertex);
                        subNetwork.add(vertexPair.getValue0());
                        subNetwork.add(vertexPair.getValue1());
                        Collections.sort(subNetwork);
                        subNetworks.add(subNetwork);
                    }
                }
            }
        }

        return (long) subNetworks.size();
    }

    public String puzzle2(){
        BronKerboschCliqueFinder<String, DefaultEdge> cliqueFinder = new BronKerboschCliqueFinder<>(network);
        List<String> maximumClique = new ArrayList<>(cliqueFinder.maximumIterator().next());
        Collections.sort(maximumClique);
        return String.join(",", maximumClique);
    }

}
