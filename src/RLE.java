public class RLE {
    //compression : by Ryan
    public void rle(String text) {
        int n = text.length();
        for(int i = 0; i < n; i++) {
            int cpt = 1;
            while(i < n - 1 && text.charAt(i) == text.charAt(i + 1) ) {
                cpt++;
                i++;
            }
            System.out.print(text.charAt(i));
            System.out.print(cpt);
        }

    }
}
