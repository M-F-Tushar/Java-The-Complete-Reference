class Light {
    public static void main(String[]args) {
        int lightspeed;
        long days, seconds, distance;

        lightspeed = 186000; // miles per second
        days = 1000; // days
        seconds = days * 24 * 60 * 60; // convert days to seconds
        distance = lightspeed * seconds; // calculate distance

        System.out.print("In " + days);
        System.out.print(" days light will travel about ");
        System.out.println(distance + " miles.");
    }

}
