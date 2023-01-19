class Solution {
    private boolean checkIPv4(String[] xs) {
        for (String x: xs) {
            if (x.length() > 1 && x.charAt(0) == '0') {
                return false;
            }
            try {
                int xValue = Integer.parseInt(x);
                if (xValue < 0 || xValue > 255) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
    private boolean checkIPv6(String[] xs) {
        for (String x: xs) {
            if (x.length() <= 0 || x.length() > 4) {
                return false;
            }
            
            for (int i = 0; i < x.length(); ++i) {
                char c = x.toLowerCase().charAt(i);
                if (c >= '0' && c <= '9') {
                    continue;
                }
                if (c > 'f') {
                    return false;
                }
            }
        }
        return true;
    }
    public String validIPAddress(String queryIP) {
        if (queryIP.length() == 0) {
            return "Neither";
        } if(queryIP.charAt(0) == '.') {
            return "Neither";
        } if(queryIP.charAt(0) == ':') {
            return "Neither";
        } if(queryIP.charAt(queryIP.length() - 1) == '.') {
            return "Neither";
        } if(queryIP.charAt(queryIP.length() - 1) == ':') {
            return "Neither";
        }
        String[] xs = queryIP.split("[.]");
        if (xs.length == 4) {
            if (checkIPv4(xs)) {
                return "IPv4";
            } else {
                return "Neither";
            }
        } else {
            String[] ys = queryIP.split(":");
            if (ys.length == 8) {
                if (checkIPv6(ys)) {
                    return "IPv6";
                } else {
                    return "Neither";
                }
            } else {
                return "Neither";
            }
        }
    }
}