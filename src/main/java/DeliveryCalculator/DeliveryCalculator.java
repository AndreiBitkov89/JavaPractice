package DeliveryCalculator;

public class DeliveryCalculator {

    public static int calculateDeliveryCost(int distanceKm, CargoDimension dimension, boolean isFragile, ServiceWorkload workload) {
        if (isFragile && distanceKm > 30) {
            throw new IllegalArgumentException("Fragile goods cannot be delivered over a distance of more than 30 km");
        }

        int baseCost = getDistanceCost(distanceKm);
        if (isFragile) {
            baseCost += 300;
        }

        double totalCost = (baseCost + dimension.getCostIncrease()) * workload.getDeliveryRate();
        return Math.max((int) Math.ceil(totalCost), 400);
    }

    private static int getDistanceCost(int distanceKm) {
        if (distanceKm > 30) return 300;
        if (distanceKm > 10) return 200;
        if (distanceKm > 2) return 100;
        return 50;
    }
}
