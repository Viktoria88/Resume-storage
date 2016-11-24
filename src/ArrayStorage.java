/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final int maxSize = 10000;
    Resume[] storage = new Resume[maxSize];
    private int size = 0;

    public void clear() {

        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {

        if (isFull()) {
            System.out.println("Storage resume crowded");
            return;
        } else if (find(r.getUuid()) >= 0) {
            System.out.println("This resume is already exists");
            return;
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        if (find(uuid) >= 0) {
            return storage[find(uuid)];
        } else {
            System.out.println("Resume with uuid " + uuid + " don't find");
            return null;
        }
    }

    public void delete(String uuid) {
        if (find(uuid) >= 0) {
            System.out.println("del" + find(uuid));
            storage[find(uuid)] = storage[size - 1];
            size--;
            return;
        } else {
            System.out.println("Resume with uuid " + uuid + " don't find");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {

        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            if (storage[i] != null)
                resumes[i] = storage[i];
        }
        return resumes;
    }

    public int size() {
        return size;
    }

    private boolean isFull() {
        if (size == maxSize) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isExists(Resume[] re, Resume r) {
        for (int i = 0; i < size; i++) {
            if (re[i].getUuid().equals(r.getUuid())) {
                System.out.println("This resume is already exists");
                return true;
            }
        }
        return false;
    }

    private int find(String uuidFound) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuidFound)) {
                System.out.println("резюме найдено");
                return i;
            }
        }
        System.out.println("резюме не найдено");
        return -1;
    }
}
