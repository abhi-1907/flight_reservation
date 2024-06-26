package booking.flight;
import java.io.*;
import java.util.*;

public class FlightData {
     static final String FILE_PATH = "flight_data.txt";

    String name;
    String source;
    String destination;
    int distance;
    int seats;
    boolean availability;

    public FlightData(String n) {
        name = n;
    }

    public FlightData(String n, String s, String d, int dist, int seat, boolean t) {
        name = n;
        source = s;
        destination = d;
        distance = dist;
        seats = seat;
        availability = t;
    }

    public void addFlightData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(name + "," + source + "," + destination + "," + distance + "," + seats + "," + availability);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFlightData() {
        List<String> flightDataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] flightData = line.split(",");
                if (!flightData[0].equals(name)) {
                    flightDataList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String flightData : flightDataList) {
                writer.write(flightData);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateFlightData(FlightData newdata) {
        List<String> flightDataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] flightData = line.split(",");
                if (flightData[0].equals(name)) {
                    flightDataList.add(newdata.name + "," + newdata.source + "," + newdata.destination + "," + newdata.distance + "," + newdata.seats + "," + newdata.availability);
                } else {
                    flightDataList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String flightData : flightDataList) {
                writer.write(flightData);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double calculateFare(int numSeats) {
        double baseFare = 100; // Assuming a base fare per seat
        return baseFare * numSeats;
    }

    // Utility method to list all flights (for testing purposes)
    public static List<FlightData> listAllFlights() {
        List<FlightData> flights = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] flightData = line.split(",");
                FlightData flight = new FlightData(flightData[0], flightData[1], flightData[2], Integer.parseInt(flightData[3]), Integer.parseInt(flightData[4]), Boolean.parseBoolean(flightData[5]));
                flights.add(flight);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flights;
    }
}

