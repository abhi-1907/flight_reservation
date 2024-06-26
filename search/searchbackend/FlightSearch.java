package search.searchbackend;


import java.util.*;
import java.io.*;

class DijkstraAlgorithm {
    private Map<String, Integer> distances = new HashMap<>();
    private Map<String, String> previous = new HashMap<>();
    private PriorityQueue<String> nodes = new PriorityQueue<>(Comparator.comparingInt(distances::get));
    private Set<String> visited = new HashSet<>();

    public List<String> findShortestPath(String source, String destination) {
        // Initialize distances and previous maps
        initializeGraph(source);

        while (!nodes.isEmpty()) {
            String current = nodes.poll();
            if (current.equals(destination)) {
                return getPath(source, destination);
            }
            if (visited.contains(current)) continue;
            visited.add(current);
            updateNeighbors(current);
        }
        return new ArrayList<>();  // Return empty list if no path is found
    }

    private void initializeGraph(String source) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("flight_paths.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String node = parts[0];
                distances.put(node, node.equals(source) ? 0 : Integer.MAX_VALUE);
                previous.put(node, null);
                nodes.add(node);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateNeighbors(String current) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("flight_paths.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String source = parts[0];
                String destination = parts[1];
                int distance = Integer.parseInt(parts[2]);

                if (source.equals(current)) {
                    int newDist = distances.get(current) + distance;
                    if (newDist < distances.get(destination)) {
                        distances.put(destination, newDist);
                        previous.put(destination, current);
                        nodes.remove(destination);
                        nodes.add(destination);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getPath(String source, String destination) {
        List<String> path = new ArrayList<>();
        for (String at = destination; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path.get(0).equals(source) ? path : new ArrayList<>();
    }
}

}

import java.util.*;
import java.io.*;

public class FlightSearch {
    public static void main(String[] args) {
        FlightSearch search = new FlightSearch();
        String source = "A"; // Replace with your source airport code
        String destination = "D"; // Replace with your destination airport code

        String flights = search.searchFlights(source, destination);
        if (flights != null) {
            System.out.println("Flights from " + source + " to " + destination + ":");
            System.out.println(flights);
        }
    }

    public String searchFlights(String source, String destination) {
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
        List<String> shortestPath = dijkstra.findShortestPath(source, destination);
        if (!shortestPath.isEmpty()) {
            String flights = fetchFlightDetails(source, destination);
            return flights;
        } else {
            System.out.println("No path found from " + source + " to " + destination);
        }
        return null;
    }

    private String fetchFlightDetails(String source, String destination) {
        StringBuilder flights = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("flights.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String flightSource = parts[1];
                String flightDestination = parts[2];
                int distance = Integer.parseInt(parts[3]);
                int seats = Integer.parseInt(parts[4]);
                boolean availability = Boolean.parseBoolean(parts[5]);

                if (flightSource.equals(source) && flightDestination.equals(destination)) {
                    String flightDetails = "\nName: " + name + "\nDistance: " + distance + " km\nSeats: " + seats + "\nAvailability: " + (availability ? "Yes" : "No");
                    flights.append(flightDetails);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flights.toString();
    }
}
