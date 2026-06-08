import os
import csv 

for f in os.listdir():
    if f.endswith('.csv') and f != "fake_data.csv" :
        print(f)
        with open(f,'r') as ff:
            reader = csv.DictReader(ff)
            data = list(reader)
            for d in data:
                d["avg_annual_appreciation"] = float(d["avg_annual_appreciation"])/100
                print(d)
#            print(data)
        with open("a"+f,'w') as fff:
            writer = csv.DictWriter(fff,fieldnames=list(data[0].keys()))
            writer.writeheader()
            writer.writerows(data)
