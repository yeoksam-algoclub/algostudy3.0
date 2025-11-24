# 1부터 1,000,000,000 까지의 양수 혹은 그 반대 부호(이진탐색 혹은 DP)

N = gets.to_i

arr = gets.split.map(&:to_i) # 오름차순 입력

left_idx = 0
right_idx = N-1

result = (arr[left_idx] + arr[right_idx]).abs
left_answer = left_idx
right_answer = right_idx
while left_idx < right_idx do # left == right => break

  sum = arr[left_idx] + arr[right_idx]
  if sum.abs < result
    result = sum.abs
    left_answer = left_idx
    right_answer = right_idx
    if sum == 0
      break
    end
  end
  if sum < 0
    left_idx = left_idx + 1
  else
    right_idx = right_idx - 1
  end
end

puts("#{arr[left_answer]} #{arr[right_answer]}")