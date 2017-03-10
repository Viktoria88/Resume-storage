package com.urise.webapp.storage.serialization;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.Section;
import com.urise.webapp.model.SectionType;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.Map;

/**
 * Created by viktoriyasidenko on 3/3/17.
 */
public class DataStreamSerialization implements StreamSerialization {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(os)){
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for(Map.Entry<ContactType, String> entry : contacts.entrySet()){
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
                for (Map.Entry<SectionType, Section> entry1 : r.getSections().entrySet()){
                    dos.writeUTF(entry1.getKey().name());
                    dos.writeUTF(entry1.getValue().toString());
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try(DataInputStream dis = new DataInputStream(is)){
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for(int i = 0; i < size; i++){
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
//                for (int j = 0; j < size; j++){
//                    SectionType sectionType = SectionType.valueOf(dis.readUTF());
//                    Section value = dis.readUTF();
//                    resume.addSection(sectionType, );
//                }
            }
            return resume;
        }
    }
}
