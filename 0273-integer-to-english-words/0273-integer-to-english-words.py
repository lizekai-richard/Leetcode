class Solution:
    
    def __init__(self):
        self.digit_to_word = {
            '0': 'Zero',
            '1': 'One',
            '2': 'Two',
            '3': 'Three',
            '4': 'Four',
            '5': 'Five',
            '6': 'Six',
            '7': 'Seven',
            '8': 'Eight',
            '9': 'Nine',
            '10': 'Ten',
            '11': 'Eleven',
            '12': 'Twelve',
            '13': 'Thirteen',
            '14': 'Fourteen',
            '15': 'Fifteen',
            '16': 'Sixteen',
            '17': 'Seventeen',
            '18': 'Eighteen',
            '19': 'Nineteen'
        }
        
        self.ten_digit_to_word = {
            '1': 'Ten',
            '2': 'Twenty',
            '3': 'Thirty',
            '4': 'Forty',
            '5': 'Fifty',
            '6': 'Sixty',
            '7': 'Seventy',
            '8': 'Eighty',
            '9': 'Ninety'
        }
    
    def process_three_digits(self, x):
        one_digit = x % 10
        ten_digit = (x // 10) % 10
        hundred_digit = (x // 100) % 10
        
        if x % 100 == 0 and x != 0:
            return self.digit_to_word[str(hundred_digit)] + ' Hundred'
        elif x == 0:
            return -1
        
        res = self.digit_to_word[str(one_digit)]
        if ten_digit == 0:
            if hundred_digit != 0:
                res = self.digit_to_word[str(hundred_digit)] + ' Hundred ' + res
            return res
        
        if ten_digit == 1:
            res = self.digit_to_word[str(ten_digit * 10 + one_digit)]
        elif one_digit == 0:
            res = self.ten_digit_to_word[str(ten_digit)]
        else:
            res = self.ten_digit_to_word[str(ten_digit)] + ' ' + res
        
        if hundred_digit != 0:
            res = self.digit_to_word[str(hundred_digit)] + ' Hundred ' + res

        return res
        
    def numberToWords(self, num: int) -> str:
        if num == 0:
            return "Zero"
        
        ans = ""
        
        ans2, ans3, ans4 = -1, -1, -1
        
        ans1 = self.process_three_digits((num % 1000))
        if num >= 1000:
            ans2 = self.process_three_digits((num // 1000) % 1000)
        
        if num >= 1000000:
            ans3 = self.process_three_digits((num // 1000000) % 1000)
        
        if num >= 1000000000:
            ans4 = self.process_three_digits((num // 1000000000) % 1000)
        
        if ans1 != -1:
            ans = ans1 + ans
            
        if ans2 != -1:
            if ans == "":
                ans = ans2 + ' Thousand'
            else:
                ans = ans2 + ' Thousand ' + ans
        
        if ans3 != -1:
            if ans == "":
                ans = ans3 + ' Million'
            else:
                ans = ans3 + ' Million ' + ans
            
        if ans4 != -1:
            if ans == "":
                ans = ans4 + ' Billion'
            else:
                ans = ans4 + ' Billion ' + ans
        return ans
        
        
        