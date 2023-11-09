import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Collections;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        ArrayList<Medicine> medicineList = new ArrayList();
        ArrayList<Allergy> allergyList = new ArrayList<>();

        String response;
        Medicine medicine;
        try {
            Scanner fileScanner = new Scanner(new File("medicine_list.txt"));
            HashMap medInfo = new HashMap();

            while (fileScanner.hasNextLine()) {
                response = fileScanner.nextLine();
                String[] entry = response.split("=");
                if (entry.length > 1) {
                    medInfo.put(entry[0].trim(), entry[1].trim());
                    if (medInfo.size() >= 6) {
                        medicine = new Medicine((String) medInfo.get("medName"), (String) medInfo.get("brandName"), Integer.parseInt((String) medInfo.get("tablets")), Integer.parseInt((String) medInfo.get("dosage")), Integer.parseInt((String) medInfo.get("refills")), (String) medInfo.get("acquired"));
                        medicineList.add(medicine);
                        medInfo = new HashMap();
                    }
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }


        try {
            Scanner fileScanner = new Scanner(new File("allergy_list.txt"));

            while (fileScanner.hasNextLine()) {
                response = fileScanner.nextLine();
                String[] entry = response.split("=");
                if (entry.length > 1) {
                    Allergy allergy = new Allergy(entry[0].trim(), entry[1].trim());
                    allergyList.add(allergy);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }


        System.out.println("Are you looking for one medicine, or do you need a list of your medicines? Type in your medicine you need to find, or type 'List' to access the list.");
        System.out.println("If you want to know what this software does, type 'Help'.");
        System.out.println("Or would you like to add a food or drug allergy to your list? If so Type 'add Allergy'");
        String input = key.nextLine().toLowerCase().replaceAll(" ", "");


        if (input.equals("help")) {
            System.out.println(Help.about());
        } else if (input.equals("list")) {
            Iterator var24 = medicineList.iterator();

            while (var24.hasNext()) {
                medicine = (Medicine) var24.next();
                System.out.println(medicine);

            }
        } else if (input.equals("allergy")) {
            for (Allergy allergy : allergyList) {
                System.out.println(allergy);
            }
        } else if (input.equals("addallergy")) { // Add a new option to add an allergy
            System.out.println("Enter the type of allergy (food/drug):");
            String allergyType = key.nextLine().toLowerCase();
            System.out.println("Enter the name of the allergy:");
            String allergyName = key.nextLine();
            Allergy newAllergy = new Allergy(allergyType, allergyName);
            allergyList.add(newAllergy);

            try {
                FileWriter writer = new FileWriter(new File("allergy_list.txt"));
                Collections.sort(allergyList);
                for (Allergy allergy : allergyList) {
                    writer.write(allergy.getType() + "=" + allergy.getName() + "\n");
                }
                writer.close();
                System.out.println("Allergy data written to file successfully.");
            } catch (IOException var19) {
                System.out.println("Error: " + var19.getMessage());
            }
        } else {
            boolean foundMedicine = false;
            Iterator var23 = medicineList.iterator();

            while (var23.hasNext()) {
                medicine = (Medicine) var23.next();
                if (medicine.getMedName().equalsIgnoreCase(input) || medicine.getBrandName().equalsIgnoreCase(input)) {
                    System.out.println(medicine);
                    foundMedicine = true;
                    break;
                }
            }

            int tablets;
            if (!foundMedicine) {
                System.out.println("Sorry, we could not find the medicine you are looking for.");
                System.out.println("Do you want to add this medicine to the list? (Y/N)");
                response = key.nextLine().toLowerCase().replaceAll(" ", "");
                if (response.equals("y")) {
                    System.out.println("Enter medicine name:");
                    String medName = key.nextLine();
                    System.out.println("Enter brand name:");
                    String brandName = key.nextLine();
                    System.out.println("Enter tablets in bottle:");
                    tablets = key.nextInt();
                    System.out.println("Enter dosage:");
                    int dosage = key.nextInt();
                    System.out.println("How may refills left:");
                    tablets = key.nextInt();
                    key.nextLine();
                    System.out.println("Type how you acquired the medicine; Prescription or Over then Counter:");
                    String prescriptionType = key.nextLine();
                    Medicine newMedicine = new Medicine(medName, brandName, tablets, dosage, tablets, prescriptionType);
                    medicineList.add(newMedicine);

                    try {
                        FileWriter writer = new FileWriter(new File("medicine_list.txt"));
                        Iterator var14 = medicineList.iterator();

                        while (var14.hasNext()) {
                            medicine = (Medicine) var14.next();
                            writer.write(medicine.toString() + "\n");
                        }

                        writer.close();
                        System.out.println("Medicine data written to file successfully.");
                    } catch (IOException var19) {
                        System.out.println("Error: " + var19.getMessage());
                    }
                }
            }

            Iterator var28;
            if (foundMedicine) {
                System.out.println("Do you want to remove this medicine from the list? (Y/N)");
                response = key.nextLine().toLowerCase().replaceAll(" ", "");
                if (response.equals("y")) {
                    var28 = medicineList.iterator();

                    label119:
                    {
                        {
                            do {
                                if (!var28.hasNext()) {
                                    break label119;
                                }

                                medicine = (Medicine) var28.next();
                            } while (!medicine.getMedName().equalsIgnoreCase(input) && !medicine.getBrandName().equalsIgnoreCase(input));

                            medicineList.remove(medicine);
                        }

                        try {
                            FileWriter writer = new FileWriter(new File("medicine_list.txt"));
                            Iterator var31 = medicineList.iterator();

                            while (var31.hasNext()) {
                                medicine = (Medicine) var31.next();
                                writer.write(medicine.toString() + "\n");
                            }

                            writer.close();
                            System.out.println("Medicine removed and data written to file successfully.");
                        } catch (IOException var18) {
                            System.out.println("Error: " + var18.getMessage());
                        }
                    }
                }

                if (foundMedicine) {
                    System.out.println("Do you want to edit this medicine? (Y/N)");
                    response = key.nextLine().toLowerCase().replaceAll(" ", "");
                    if (response.equals("y")) {
                        var28 = medicineList.iterator();

                        do {
                            if (!var28.hasNext()) {
                                return;
                            }

                            medicine = (Medicine) var28.next();
                        } while (!medicine.getMedName().equalsIgnoreCase(input) && !medicine.getBrandName().equalsIgnoreCase(input));

                        System.out.println("Enter medicine name:");
                        String medName = key.nextLine();
                        System.out.println("Enter brand name:");
                        String brandName = key.nextLine();
                        System.out.println("Enter tablets in bottle:");
                        tablets = key.nextInt();
                        System.out.println("Enter dosage:");
                        int dosage = key.nextInt();
                        System.out.println("How many refills left:");
                        int refills = key.nextInt();
                        key.nextLine();
                        System.out.println("Enter prescription type:");
                        String prescriptionType = key.nextLine();
                        medicine.setMedName(medName);
                        medicine.setBrandName(brandName);
                        medicine.setTablets(tablets);
                        medicine.setDosage(dosage);
                        medicine.setRefills(refills);
                        medicine.setAcquired(prescriptionType);

                        try {
                            FileWriter writer = new FileWriter(new File("medicine_list.txt"));
                            Iterator var39 = medicineList.iterator();

                            while (var39.hasNext()) {
                                Medicine med = (Medicine) var39.next();
                                writer.write(med.toString() + "\n");
                            }

                            writer.close();
                            System.out.println("Medicine edited and data written to file successfully.");
                        } catch (IOException var17) {
                            System.out.println("Error: " + var17.getMessage());
                        }
                    }
                }

            }
        }
    }
}