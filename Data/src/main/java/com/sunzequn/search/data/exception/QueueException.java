package com.sunzequn.search.data.exception;

/**
 * Created by Sloriac on 15/11/21.
 * <p>
 * Handle exceptions about the custom queues,
 * that is <code>Queue</code> and <code>UrlQueue</code>.
 */
public class QueueException extends Exception {

    public QueueException() {
        super();
    }

    public QueueException(String message) {
        super(message);
    }
}
