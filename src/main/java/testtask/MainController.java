package testtask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class MainController {

    private static int currentPage = 1;
    private static int pageSize = 10;
    private static int filter = 2;
    private static String findItem = "";

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/")
    public String main(@RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size,
                       @RequestParam("find") Optional<String> itemName,
                       @RequestParam("filter") Optional<Integer> filterNecessity,
                       Model model) {

        size.ifPresent(s -> pageSize = s);
        page.ifPresent(p -> currentPage = p);
        filterNecessity.ifPresent(f -> filter = f);
        itemName.ifPresent(f -> findItem = f);

        model.addAttribute("find", findItem);
        model.addAttribute("filter", filter);

        PageRequest pageable = PageRequest.of(currentPage-1, pageSize);
        Page<Item> itemPage;
        int numOfItems;

        if ((filter == 0 || filter == 1) && !findItem.isEmpty()) {
            itemPage = itemRepository.findByNameAndNecessity(findItem, filter, pageable);
        } else if (filter == 0 || filter == 1) {
            itemPage = itemRepository.findByNecessity(filter, pageable);
        } else if (!findItem.isEmpty()) {
            itemPage = itemRepository.findByName(findItem, pageable);
        } else {
            itemPage = itemRepository.findAll(pageable);
        }

        List<Item> itemsPerPage;
        if (itemPage.getSize() == 0) {
            itemsPerPage = Collections.EMPTY_LIST;
            currentPage = 1;
            model.addAttribute("message", "emptyList");
        } else {
            itemsPerPage = itemPage.getContent();
        }
        numOfItems = (int) itemPage.getTotalElements();
        return showMainPage(model, itemsPerPage, numOfItems);
    }

    private String showMainPage(Model model, List<Item> itemsPerPage, int numOfItems){
        if (itemsPerPage.size() > 0) {
            calcNumOfKits(model);
            int totalPages = (int) Math.ceil(1.0* numOfItems / pageSize);
            model.addAttribute("itemsPerPage", itemsPerPage);
            model.addAttribute("currentPage", currentPage);

            if (totalPages > 1) {
                List<Integer> pageNumbers = Stream.of(currentPage - 2, currentPage - 1, currentPage, currentPage + 1,
                        currentPage + 2)
                        .filter((s) -> s > 0 && s <= totalPages)
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList());
                Map<Integer, String> pageNumbersMap = new TreeMap<>();
                for (Integer pageNumber: pageNumbers){
                    pageNumbersMap.put(pageNumber, String.valueOf(pageNumber));
                }
                if (currentPage > 3) {
                    pageNumbersMap.replace(pageNumbers.get(0), "<<");
                }
                if (currentPage < totalPages - 2) {
                    pageNumbersMap.replace(pageNumbers.get(pageNumbers.size() - 1), ">>");
                }
                model.addAttribute("pageNumbersMap", pageNumbersMap);
            }
        }
        return "index";
    }

    private void calcNumOfKits(Model model) {
        List<Item> necessaryItems = itemRepository.findByNecessity(1);
        int numOfKits = Collections.min(necessaryItems,(Comparator.comparingInt(Item::getAmount))).getAmount();
        model.addAttribute("numOfKits", numOfKits);
    }

    @GetMapping("/add")
    public String addNewItem(Model model) {
        model.addAttribute("item", new Item());
        return "edit";
    }

    @GetMapping("/edit")
    public String editItem(@RequestParam("id") Integer id, Model model) {
        Item item = itemRepository.findById(id).get();
        model.addAttribute("item", item);
        return "edit";
    }

    @PostMapping("/save")
    public String saveNewItem(@ModelAttribute Item newItem, Model model) {
        String result;
        if (validParameters(newItem, model)) {
            itemRepository.save(newItem);
            result = "redirect:/";
        } else {
            model.addAttribute("message", "notValidParameters");
            result = "edit";
        }
        return result;
    }

    private boolean validParameters(Item item, Model model) {
        Boolean result = true;
        if (item.getName() == null || item.getName().isEmpty()) {
            model.addAttribute("errorMessage", "nameMissing");
            result = false;
        } else if (item.getNecessity() == null){
            model.addAttribute("errorMessage", "necessityMissing");
            result = false;
        } else if (item.getAmount() == null || item.getAmount() <= 0){
            model.addAttribute("errorMessage", "amountMissing");
            result = false;
        }
        return result;
    }

    @GetMapping("/delete")
    public String main(@RequestParam("id") Integer id, Model model) {
        itemRepository.deleteById(id);
        return "redirect:/";
    }

}