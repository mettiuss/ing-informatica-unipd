import java.util.Scanner;
import java.io.*;

public class DNAProfile {
    public static void main(String[] args) {
        String profile = "";
        try (FileReader profileFile = new FileReader(args[1]); Scanner profileScanner = new Scanner(profileFile)) {
            profile = profileScanner.nextLine();
        } catch (IOException e) {
            System.out.println("Profile file not found!");
            System.exit(1);
        }

        try (FileReader profilesFile = new FileReader(args[0]); Scanner profiles = new Scanner(profilesFile)) {
            profiles.next(); // remove name
            String rawIdentifiers = profiles.nextLine();
            Scanner identifiers = new Scanner(rawIdentifiers);
            int[] profileNumbers = new int[countIdentifiers(rawIdentifiers)];
            for (int i = 0; i < profileNumbers.length; i++) {
                profileNumbers[i] = countOccurences(profile, identifiers.next());
            }

            boolean anyMatch = false;
            while (profiles.hasNextLine()) {
                String compareProfile = profiles.nextLine();
                Scanner compare = new Scanner(compareProfile);
                String name = compare.next();
                boolean equal = true;
                for (int i = 0; i < profileNumbers.length; i++) {
                    if (profileNumbers[i] != Integer.parseInt(compare.next()))
                        equal = false;
                }
                if (equal) {
                    anyMatch = true;
                    System.out.println(name + " is equal to the profile");
                }
                compare.close();
            }

            if (!anyMatch) {
                System.out.println("Nessun match trovato");
            }
            identifiers.close();
        } catch (IOException e) {
            System.out.println("Profiles file not found!");
            System.exit(1);
        }
    }

    private static int countIdentifiers(String identifiers) {
        int l = 0;
        Scanner s = new Scanner(identifiers);
        while (s.hasNext()) {
            s.next();
            l++;
        }
        return l;
    }

    private static int countOccurences(String profile, String pattern) {
        int occurences = 0;
        for (int i = 0; i < profile.length() - pattern.length(); i++) {
            if (pattern.equals(profile.substring(i, i + pattern.length()))) {
                occurences++;
            }
        }
        return occurences;
    }
}