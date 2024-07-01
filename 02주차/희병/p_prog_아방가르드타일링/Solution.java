class Solution {
    final int MOD = 1000000007;
    
    public int solution(int n) {
        long[] mem = new long[n + 1];
        long[] mem4 = new long[n + 1];
        long[] mem5 = new long[n + 1];
        long[] mem6 = new long[n + 1];
        
        mem[0] = 1;
        for (int i = 1; i <= n; i++) {
            mem[i] = (mem[i] + mem[i - 1]) % MOD;
            if (i >= 2) mem[i] = (mem[i] + mem[i - 2] * 2) % MOD;
            if (i >= 3) mem[i] = (mem[i] + mem[i - 3] * 5) % MOD;
            if (i >= 4) {
                long diff = mem[i - 4] * 2 % MOD;
                mem4[i] = (mem4[i - 3] + diff) % MOD;
                mem[i] = (mem[i] + mem4[i]) % MOD;
            }
            if (i >= 5) {
                long diff = mem[i - 5] * 2 % MOD;
                mem5[i] = (mem5[i - 3] + diff) % MOD;
                mem[i] = (mem[i] + mem5[i]) % MOD;
            }
            if (i >= 6) {
                long diff = mem[i - 6] * 4 % MOD;
                mem6[i] = (mem6[i - 3] + diff) % MOD;
                mem[i] = (mem[i] + mem6[i]) % MOD;
            }
        }
        return Math.toIntExact(mem[n]);
    }
}
