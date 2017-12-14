#read file
read_file = open('discnt .in', 'r')
#split file by lines
input_data = read_file.readline().split()

#convert input data to float
for i in range(input_data.__len__()):
    input_data[i] = float(input_data[i])

#convert discount to float value
discount = float(read_file.readlines()[0])

#sort in reverse mode for bigger discounts
input_data.sort(reverse=True)

#calculate how many items have discount and calculate them
for i in range(input_data.__len__()//3):
    input_data[i] = input_data[i]*(100-discount)/100

write_file = open('discnt .out', 'w')
#write minimal price to file
write_file.write("%.2f" % sum(input_data))
read_file.close()