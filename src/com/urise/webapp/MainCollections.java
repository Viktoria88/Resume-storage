package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.util.*;

/**
 * Created by viktoriyasidenko on 1/27/17.
 */
public class MainCollections {

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUM_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUM_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUM_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUM_4 = new Resume(UUID_4);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUM_1);
        collection.add(RESUM_2);
        collection.add(RESUM_3);

        for (Resume r : collection) {
            System.out.println(r);
            if(Objects.equals(r.getUuid(), UUID_1)){
//                collection.remove(r);
            }
        }

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()){
                Resume r = iterator.next();
                System.out.println(r);
                if(Objects.equals(r.getUuid(), UUID_1)){
                iterator.remove();
                }
        }

        System.out.println(collection.toString());

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUM_1);
        map.put(UUID_2, RESUM_2);
        map.put(UUID_3, RESUM_3);

        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
