package ru.job4j.ood.lsp;

public class Exp2 {
    class Ipoteca {
        int startCapital;
        int persent;
        int summ;

        public Ipoteca(int startCapital, int persent, int summ) {
            this.startCapital = startCapital;
            this.persent = persent;
            this.summ = summ;
        }

        public int get(int startCapital, int summ) {
            //Logic
            int dengy = 0;
            return dengy;
        }
    }

    class IpotecaDlyaSvoih extends Ipoteca {
        public IpotecaDlyaSvoih(int startCapital, int persent, int summ) {
            super(startCapital, persent, summ);
            /*
            Ипотека "для своих" выдаётся без проблем с любым первоначальным вкладом.
             */
        }
    }

    class IpotecaDlyaVseh extends Ipoteca {

        protected int border = 400;

        public IpotecaDlyaVseh(int startCapital, int persent, int summ) {
            super(startCapital, persent, summ);
        }

        @Override
        public int get(int startCapital, int summ) {
            /*
            В ипотеке "для всех" появляется проверка на стартовый капитал
             */
            if (startCapital < 400) {
                throw new IllegalArgumentException("Malo deneg!");
                /*
                Очевидно мы ограничиваем поведение родительского класса.
                Так делать нельзя.
                 */
            }
            return super.get(startCapital, summ);
        }
    }
}


