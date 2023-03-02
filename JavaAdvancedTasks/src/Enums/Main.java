package Enums;

public class Main {

    public static void main(String[] args) {

        PackageSize packageSize = PackageSize.LARGE;
        System.out.println(packageSize);

    }

    enum PackageSize {
        SMALL(10, 20),
        MEDIUM(20, 30),
        LARGE(30, 40);

        private int minSize, maxSize;

        PackageSize(int minSize, int maxSize) {
            this.minSize = minSize;
            this.maxSize = maxSize;
        }

        public static PackageSize getPackageSize(int minSize, int maxSize) {
            for (PackageSize packageSize : values())
                if (minSize >= packageSize.minSize && maxSize < packageSize.maxSize) {
                    return packageSize;
                }

            return null;
        }

    }

}



