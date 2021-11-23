//package com.marcelo.algafood.api.generic;
//
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//public abstract class GenericController<T extends GenericEntity<T>> {
//
//    private final GenericService<T> service;
//
//    public GenericController(GenericRepository<T> repository) {
//        this.service = new GenericService<T>(repository) {
//        };
//    }
//
//    @GetMapping("")
//    public ResponseEntity<Page<T>> getPage(Pageable pageable) {
//        return ResponseEntity.ok(service.getPage(pageable));
//    }
//
//    @PostMapping("save")
//    public ResponseEntity<T> saveAndFlush(@RequestBody T created) {
//        return ResponseEntity.ok(service.save(created));
//    }
//
//    @GetMapping("findById/{id}")
//    public ResponseEntity<T> findById(@PathVariable Integer Id) {
//        return ResponseEntity.ok(service.get(Id));
//    }
//
//
//    @PutMapping("update")
//    public ResponseEntity<T> update(@RequestBody T updated) {
//        return ResponseEntity.ok(service.update(updated));
//    }
//
////    @DeleteMapping("delete")
////    public Map<String, Boolean> delete(@RequestParam Integer Id) throws ResourceNotFoundException {
////        Map<String, Boolean> response = new HashMap<>();
////        return response;
////    }
//
//    @GetMapping("findAllByPage")
//    public ResponseEntity<Page<T>> findAllByPage(Pageable pageable) {
//        return ResponseEntity.ok(service.getPage(pageable));
//    }
//
//    public ResponseEntity<?> listAll(Pageable pageable) {
//        return null;
//    }
//}