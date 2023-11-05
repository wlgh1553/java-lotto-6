package lotto.service;

import static lotto.domain.ErrorMessages.NOT_INTEGER;
import static lotto.domain.ErrorMessages.PURCHASE_RANGE;
import static lotto.domain.ErrorMessages.PURCHASE_UNIT;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;

public class PurchaseLotto {
    public List<Lotto> purchase(String cost) {
        int numberOfLotto = validateNumberOfLotto(cost);
        return getLottos(numberOfLotto);
    }

    private int validateNumberOfLotto(String cost) {
        int numberCost = toNumber(cost);
        if (!checkRange(numberCost)) {
            throw new IllegalArgumentException(PURCHASE_RANGE.getMessage());
        }
        if (!checkUnit(numberCost)) {
            throw new IllegalArgumentException(PURCHASE_UNIT.getMessage());
        }
        return numberCost / 1000;
    }

    private boolean checkRange(int number) {
        return number >= 1000 && number <= 1_000_000;
    }

    private boolean checkUnit(int number) {
        return number % 1000 == 0;
    }

    private int toNumber(String cost) {
        int result;
        try {
            result = Integer.parseInt(cost);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER.getMessage());
        }
        return result;
    }

    private List<Lotto> getLottos(int numberOfLottos) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numberOfLottos; i++) {
            lottos.add(getLotto());
        }
        return lottos;
    }

    private Lotto getLotto() {
        return new Lotto(getRandomNumbers());
    }

    private List<Integer> getRandomNumbers() {
        return new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
    }
}
