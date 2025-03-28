package edu.kis.vh.nursery;

/**
 * Klasa FIFORhymer rozszerza klasę DefaultCountingOutRhymer i implementuje
 * zasadę działania stosu FIFO (First In, First Out), czyli pierwszy element
 * wchodzi na stos jako pierwszy wychodzi. W tej implementacji elementy są
 * przechowywane w tymczasowym stosie, aby zapewnić poprawność działania
 * FIFO.
 */
public class FIFORhymer extends DefaultCountingOutRhymer {

    /**
     * Zmienna instancyjna przechowująca tymczasowy stos, używany do
     * implementacji logiki FIFO.
     */
    private final DefaultCountingOutRhymer temp = new DefaultCountingOutRhymer();

    /**
     * Przesuwa elementy z głównego stosu do tymczasowego, aby zachować zasadę FIFO.
     * Następnie zwraca pierwszy element, który został dodany do stosu.
     *
     * @return element typu int, który był pierwszym dodanym do stosu (FIFO).
     */
    @Override
    public int countOut() {
        // Przenosimy elementy z głównego stosu do tymczasowego, zachowując ich kolejność.
        while (!callCheck())
            temp.countIn(super.countOut());

        // Pobieramy pierwszy element z tymczasowego stosu (FIFO).
        int ret = temp.countOut();

        // Przywracamy pozostałe elementy z tymczasowego stosu z powrotem na główny stos.
        while (!temp.callCheck())
            countIn(temp.countOut());

        // Zwracamy pierwszy element z FIFO.
        return ret;
    }

    /**
     * Zwraca referencję do tymczasowego stosu, który jest wykorzystywany do
     * realizacji operacji FIFO.
     *
     * @return obiekt typu DefaultCountingOutRhymer, reprezentujący tymczasowy stos.
     */
    public DefaultCountingOutRhymer getTemp() {
        return temp;
    }
}
