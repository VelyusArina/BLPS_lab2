package ru.artemiyandarina.blps_lab2.configs;

import bitronix.tm.recovery.RecoveryException;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/**
 * Interface defining the methods to be implemented by transaction journals.
 */
public interface Journal extends Closeable, Flushable {

    /**
     * Open the journal.
     *
     * @throws IOException if an I/O error occurs.
     */
    void open() throws IOException;

    /**
     * Log a transaction record to the journal.
     *
     * @param record the record to log.
     * @throws IOException if an I/O error occurs.
     */
    <JournalRecord> void log(JournalRecord record) throws IOException;

    /**
     * Perform any necessary recovery operations.
     *
     * @throws RecoveryException if recovery fails.
     */
    void recover() throws RecoveryException;

    /**
     * Forces any changes in the journal to be written to the underlying storage.
     *
     * @throws IOException if an I/O error occurs.
     */
    void force() throws IOException;

}
