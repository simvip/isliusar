package wait;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ivan Sliusar on 02.11.2017.
 * Red Line Soft corp.
 */
public class ProducerAndConsumerTest {
    /**
     * Test.
     */
    @Test
    public void consumerTask() {
        String inputString = "The Queue interface defines some methods for acting on the first element of the list, which differ in the way they behave, and the result they provide. For the purposes of the OCPJP7 certification exam is important to know how these methods work and to recognize precisely how each of them behaves in some special situations.";
        ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer(inputString);
        producerAndConsumer.startProgram();
    }

}