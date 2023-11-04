package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 범위가 0이하 또는 46이상이면 예외가 발생한다.")
    @Test
    void createLottoByInvalidRangeNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또끼리 비교하여 같은 것 개수 알아내기")
    @Test
    void compareLotto() {
        Lotto A = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto B = new Lotto(List.of(2, 3, 4, 5, 6, 7));
        assertThat(A.compareTo(B) == 5).isTrue();

        Lotto C = new Lotto(List.of(10, 11, 12, 13, 14, 15));
        assertThat(A.compareTo(C) == 0).isTrue();
    }
}