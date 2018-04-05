package com.grsu.committee.dataaccess;

import com.grsu.committee.entities.*;
import com.grsu.committee.table.*;
import com.grsu.committee.utils.Utils;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;
import java.util.Properties;

public abstract class AbstractDao<T extends AbstractTable<E>, E extends AbstractModel> implements IXmlDao<E> {

    private final XStream xStream;
    private final String rootFolderPath;

    protected AbstractDao() {
        super();
        this.rootFolderPath = Utils.getProperties().getProperty("rootFolderPath");
        final File rootFolder = new File(rootFolderPath);
        if (!rootFolder.exists()) {
            rootFolder.mkdirs();
        }
        xStream = new XStream();
        xStream.processAnnotations(new Class[]{getTableClass()});

        Class<?>[] classes = new Class[]{Administrator.class, AdministratorTable.class,
                Enrollee.class, EnrolleeTable.class, Faculty.class, FacultyTable.class,
                Sheet.class, SheetTable.class};
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(classes);
    }

    @Override
    public void save(E entity) {
        // set ID
        entity.setId(getNextId());
        // get existing data
        final T table = deserializeFromXml();
        // add new row
        table.getRows().add(entity);
        // save data
        serializeToXml(table);
    }

    @Override
    public E get(Long id) {
        // get existing data
        final T table = deserializeFromXml();
        // find by ID
        for (final E row : table.getRows()) {
            if (row.getId().equals(id)) {
                return row;
            }
        }
        return null;
    }

    @Override
    public List<E> getAll() {
        // get existing data
        final T table = deserializeFromXml();
        return table.getRows();
    }

    @Override
    public void delete(Long id) {
        // get existing data
        final T table = deserializeFromXml();
        // find by ID
        E toBeDeleted = null;
        for (final E row : table.getRows()) {
            if (row.getId().equals(id)) {
                // found!!!
                toBeDeleted = row;
                break;
            }
        }
        // remove from list
        table.getRows().remove(toBeDeleted);
        // save updated table
        serializeToXml(table);
    }

    public void saveOrUpdate(E entity) {
        if (entity.getId() == null) {
            save(entity);
        } else {
            update(entity);
        }
    }

    /**
     * Reads file from FS and makes deserialization Xml->Java.
     *
     * @param xml
     * @return
     */
    @SuppressWarnings("unchecked")
    protected T deserializeFromXml() {
        final String xml = readFromFile();
        if (StringUtils.isBlank(xml)) {
            try {
                return getTableClass().newInstance();
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }
        return (T) xStream.fromXML(xml);
    }

    /**
     * Makes serialization Java->Xml and saves to FS.
     *
     * @param table
     * @return
     */
    protected void serializeToXml(final T table) {
        final String xml = xStream.toXML(table);
        writeToFile(xml);
    }

    /**
     * Writes file to the local FS. Uses
     * by.grsu.pe.dataaccess.AbstractDao.getTableClass() to resolve filename.
     *
     * @param xml data to be written in file
     */
    private void writeToFile(final String xml) {
        try {
            final File file = new File(getFileName());
            if (!file.exists()) {
                file.createNewFile();
            }
            IOUtils.write(xml, new FileOutputStream(file));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads file from the local FS. Uses
     * by.grsu.pe.dataaccess.AbstractDao.getTableClass() to resolve filename.
     *
     * @return XML data as String
     */
    private String readFromFile() {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            IOUtils.copy(new FileInputStream(getFileName()), output);
        } catch (final FileNotFoundException e) {
            return null;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return new String(output.toByteArray());
    }

    private String getFileName() {
        return new File(getRootFolderPath(), getTableClass().getSimpleName() + ".xml").getAbsolutePath();
    }

    public String getRootFolderPath() {
        return rootFolderPath;
    }

    /**
     * Will be used as ID generator (enough for training project).
     *
     * @return
     */
    protected Long getNextId() {
        return System.nanoTime();
    }

    protected abstract Class<T> getTableClass();


}
